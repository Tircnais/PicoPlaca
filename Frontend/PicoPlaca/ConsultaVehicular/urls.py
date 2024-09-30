from django.urls import path
# para USAR url en lugar de PATH
from django.conf.urls import include
# para acceso controlado por LOGEO 
from django.contrib.auth.views import login_required

from . import views

# es necesario importar las VISTAS
from .views import *


urlpatterns = [
    path('', views.index, name='home'),
    # Funciones Ajax
    path('consultaSugerencia/', views.verificaCirculacion, name='ajaxCirculacion'),
]