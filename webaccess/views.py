# Create your views here.
from django.shortcuts import render_to_response
from django.template import RequestContext
from django.contrib.auth import authenticate, login
from django.http import HttpResponse

def about(request):
    return render_to_response('about.html')

def login_user(request):
    state = 'Please log in below...'
    username = password = ''
    if request.method == "POST":
        username = request.POST.get('username')
        password = request.POST.get('password')
        user = authenticate(username=username, password=password)
        if user is not None:
            if user.is_active:
                login(request, user)
                request.session['member_id'] = user.id
                return HttpResponse("You are now logged in.")
            else:
                state = 'Account is inactive.'
        else:
            state = "Your username and/or password were incorrect."
    return render_to_response('login.html',RequestContext(request,{'state' : state, 'username' : username, 'password' : password}))
