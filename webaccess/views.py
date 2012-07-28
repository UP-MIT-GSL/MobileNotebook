# Create your views here.
from django.shortcuts import render_to_response
from django.template import RequestContext
from django.contrib.auth import authenticate, login
from django.http import HttpResponse

def about(request):
    return render_to_response('about.html')

def login_user(request):
	return render_to_response('login.html')
