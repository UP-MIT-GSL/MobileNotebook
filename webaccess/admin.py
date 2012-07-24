from django.contrib import admin
from webaccess.models import User
from webaccess.models import School
from webaccess.models import Professor
from webaccess.models import Note
from webaccess.models import Member
from webaccess.models import CourseNumber
from webaccess.models import Enroll
from webaccess.models import Membership
from webaccess.models import WriteNote

admin.site.register(School)
admin.site.register(Professor)
admin.site.register(User)
admin.site.register(Note)
admin.site.register(Member)
admin.site.register(CourseNumber)
admin.site.register(Enroll)
admin.site.register(Membership)
admin.site.register(WriteNote)
