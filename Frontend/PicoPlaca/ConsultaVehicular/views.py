# Redireccionar a paginas
from django.urls import reverse_lazy
# Tipos de salidas a template
from django.shortcuts import render, get_object_or_404

import requests
# Para el ajax (registro Competencias)
from django.http import JsonResponse

# necesaro para AJAX
from django.views.decorators.csrf import csrf_exempt
# obtener la fecha del sistema
from datetime import datetime

feriados = [
    "2024-09-01", "2024-12-24", "2024-12-25", "2024-12-31", "2024-01-01"
]

def getListApi(url):
    response = requests.get(url)
    dataJson = ''
    if response.status_code == 200:
        dataJson = response.json()  # Convierte la respuesta en JSON
    else:
        dataJson = {"error": "No se pudo consultar la API"}
    return dataJson

def index(request):
    urlRestriccion = "http://127.0.0.1:8005/api/v1/Restriccion/Lista"  # API Lista de restriciones por dia
    urlHorario = "http://127.0.0.1:8005/api/v1/HorarioRestriccion/Lista"  # API Lista de horario
    urlVehiculo = "http://127.0.0.1:8005/api/v1/Vehiculo/Lista"  # API Lista de horario
    
    dataRestriccion = getListApi(urlRestriccion)
    dataHorario = getListApi(urlHorario)
    dataVehiculo = getListApi(urlVehiculo)
    
    resumen = dict()
    resumen['Dias'] = dataRestriccion
    resumen['Horario'] = dataHorario
    resumen['Placas'] = dataVehiculo
    
    # print(resumen)
    # print(objModel.__str__)
    # print(objModel.__unicode__)
    cntxtIndex = {
        'resumen': resumen,
    }
    return render(request, "index.html", cntxtIndex)


# Función para obtener si es feriado
def es_feriado():
    fecha_actual = datetime.now().date()
    return str(fecha_actual) in feriados

# Función para obtener el día de la semana en formato string
def obtener_dia_semana():
    dias_semana = ['lunes', 'martes', 'miércoles', 'jueves', 'viernes', 'sábado', 'domingo']
    dia_semana_num = datetime.now().weekday()  # Lunes = 0, Domingo = 6
    return dias_semana[dia_semana_num]

# Función para verificar si el último dígito de la placa tiene restricción según el día y la hora
def verificar_restriccion(ultimo_digito):
    urlRestriccion = "http://127.0.0.1:8005/api/v1/Restriccion/Lista"  # API Lista de restriciones por dia
    urlHorario = "http://127.0.0.1:8005/api/v1/HorarioRestriccion/Lista"  # API Lista de horario
    dia_actual = obtener_dia_semana()

    # Si es sábado, domingo o feriado, hay libre circulación todo el día
    if dia_actual in ['sábado', 'domingo'] or es_feriado():
        return "Puede circular.<br>Libre circulación todo el día"
    
    restricciones = getListApi(urlRestriccion)
    # Verificar si el último dígito por el día de restricción
    placa_restringida = False
    for restriccion in restricciones:
        if restriccion['dia'] == dia_actual and restriccion['ultimo_digito'] == str(ultimo_digito):
            placa_restringida = True
            break

    # Si la placa no está restringida hoy, se puede circular todo el día
    if not placa_restringida:
        return "Puede circular.<br>Libre circulación todo el día"

    # Si la placa está restringida, verificar la hora
    hora_actual = datetime.now().time()
    dataHorario = getListApi(urlHorario)
    hora_inicio_manana = ''
    hora_fin_manana = ''
    hora_inicio_tarde = ''
    hora_fin_tarde = ''
    if dataHorario['tipo'] == 'mañana':
        hora_inicio_manana = datetime.strptime(dataHorario['hora_inicio'], "%H:%M:%S").time()
        hora_fin_manana = datetime.strptime(dataHorario['hora_fin'], "%H:%M:%S").time()
    else:
        hora_inicio_tarde = datetime.strptime(dataHorario['hora_inicio'], "%H:%M:%S").time()
        hora_fin_tarde = datetime.strptime(dataHorario['hora_fin'], "%H:%M:%S").time()
    
    # Verificar si la hora actual está dentro de los rangos de restricción
    if hora_inicio_manana <= hora_actual <= hora_fin_manana or hora_inicio_tarde <= hora_actual <= hora_fin_tarde:
        return "No puede circular.<br>Restricción por horario"
    else:
        return "Puede circular.<br>Libre circulación fuera de las horas restringidas"
    
    
@csrf_exempt
def verificaCirculacion(request):
    '''
        Ajax que permite obtener el numero de placa

            Parametros
            ----------
                request : request
                    Ayuda a obtener el valor de la funcion ajax
            
            Returns:
            ----------
                JsonResponse: Con el diccionario del detalle requerido
    '''
    if request.headers.get('X-Requested-With') == 'XMLHttpRequest':
        context ={}
        ultimo_digito = request.POST['ultDigito']
        # valor que se toma del ajax seccion data
        print('Digito:\t', ultimo_digito)
        
        # Verificar día y último dígito de la placa
        mensajeCirculacion = verificar_restriccion(ultimo_digito)

        print('Salida\t', mensajeCirculacion)
        context['msj']= mensajeCirculacion
        return JsonResponse(context, safe=False)
    

