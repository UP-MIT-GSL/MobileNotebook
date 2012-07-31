from django.db import models
from django.contrib.auth.models import User, UserManager, Group
from django.contrib import admin
from django.db.models.signals import post_save

class School(models.Model):
    name = models.CharField(max_length=50)
    def __unicode__(self):
        return self.name

class Professor(models.Model):
    firstname = models.CharField(max_length=30)
    lastname = models.CharField(max_length=30)
    def __unicode__(self):
        return self.firstname + " " + self.lastname

class Class(Group):
    schedule = models.CharField(max_length=20)
    professor = models.ForeignKey(Professor)
    def __unicode__(self):
        return self.name

class CustomUser(User):
    school = models.ForeignKey(School)
    coursenumbers = models.ManyToManyField(Class, through='Enroll')
    objects = UserManager()

    def __unicode__(self):
        return self.first_name + " " + self.last_name

    def create_customuser(sender,instance,created, **kwargs):
        if created:
            profile, created = CustomUser.objects.get_or_create(user=instance)
        post_save.connect(create_customuser, sender=User)

class Enroll(models.Model):
    user = models.ForeignKey(CustomUser)
    coursenumber = models.ForeignKey(Class)
    date_joined = models.DateField()

    def __unicode__(self):
        return self.user.first_name + " is enrolled to " + self.coursenumber.name

class Note(models.Model):
    title = models.CharField(max_length=50)
    link = models.CharField(max_length=100)
    def __unicode__(self):
        return self.title

class WriteNote(models.Model):
    user = models.ForeignKey(CustomUser)
    notes = models.ForeignKey(Note)
    date_written = models.DateField()

    def __unicode__(self):
        return self.user.firstname + " wrote " + self.notes.title
