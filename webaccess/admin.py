from django.contrib import admin
from webaccess.models import CustomUser
from webaccess.models import School
from webaccess.models import Professor
from webaccess.models import Note
from webaccess.models import Class
from webaccess.models import Enroll
from webaccess.models import WriteNote

admin.site.register(School)
admin.site.register(Professor)
admin.site.register(CustomUser)
admin.site.register(Note)
admin.site.register(Class)
admin.site.register(Enroll)
admin.site.register(WriteNote)
