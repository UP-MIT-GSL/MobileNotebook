# Create your views here.
from django.shortcuts import render_to_response
from django.template import RequestContext
from django.contrib.auth import authenticate, login
from django.http import HttpResponse
#from django.db.models import CustomUser

def about(request):
    return render_to_response('about.html')

def login_user(request):
    state = 'Please log in below...'
    username = password = ''
    if request.method == "POST":
        if 'login' in request.POST:
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
        elif 'register' in request.POST:
            #add to database
            #custom_user = CustomUser(first_name= request.POST.get('firstname'),last_name= request.POST.get('lastname'),email= request.POST.get('email'),user_name= request.POST.get('username'))
            from django.core.mail import send_mail
            #send_mail('Mobile Notebook Registration','Hi ' + request.POST.get('firstname') + ' ' + request.POST.get('lastname') + ', You are successfully registered to Mobile Notebook.','acorense@infobuilder.net',[request.POST.get('email')])
            state = 'Registered Successfully!'
    return render_to_response('login.html',RequestContext(request,{'state' : state, 'username' : username, 'password' : password}))
