from django.db import models
#from django.contrib.auth.models import User
from django.contrib import admin

class School(models.Model):
    name = models.CharField(max_length=50)
    def __unicode__(self):
        return self.name

class Professor(models.Model):
    firstname = models.CharField(max_length=30)
    lastname = models.CharField(max_length=30)
    def __unicode__(self):
        return self.firstname + " " + self.lastname

class CourseNumber(models.Model):
    name = models.CharField(max_length=10)
    schedule = models.CharField(max_length=20)
    professor = models.ForeignKey(Professor)
    def __unicode__(self):
        return self.name

class User(models.Model):
    firstname = models.CharField(max_length=50)
    lastname = models.CharField(max_length=50)
    username = models.CharField(max_length=50)
    password = models.CharField(max_length=50)
    email = models.CharField(max_length=100)
    school = models.ForeignKey(School)
    coursenumbers = models.ManyToManyField(CourseNumber, through='Enroll')

    def __unicode__(self):
        return self.firstname + " " + self.lastname

class Enroll(models.Model):
    user = models.ForeignKey(User)
    coursenumber = models.ForeignKey(CourseNumber)
    date_joined = models.DateField()

    def __unicode__(self):
        return self.user.firstname + " is enrolled to " + self.coursenumber.name

class Notes(models.Model):
    title = models.CharField(max_length=50)
    link = models.CharField(max_length=100)
    def __unicode__(self):
        return self.title

class WriteNotes(models.Model):
    user = models.ForeignKey(User)
    notes = models.ForeignKey(Notes)
    date_written = models.DateField()

    def __unicode__(self):
        return self.user.firstname + " wrote " + self.notes.title

class Group(models.Model):
    name = models.CharField(max_length=50)
    def __unicode__(self):
        return self.name

class Membership(models.Model):
    user = models.ForeignKey(User)
    group = models.ForeignKey(Group)
    date_joined = models.DateField()

    def __unicode__(self):
        return self.user.firstname + " is a member of " + self.group.name

class Member(models.Model):
    user = models.ForeignKey(User)
    coursenumber = models.ForeignKey(CourseNumber)  


admin.site.register(School)
admin.site.register(Professor)
admin.site.register(User)
admin.site.register(Notes)
admin.site.register(Group)
admin.site.register(Member)
admin.site.register(CourseNumber)
admin.site.register(Enroll)
admin.site.register(Membership)
admin.site.register(WriteNotes)