# Generated by Django 5.0.6 on 2024-08-26 09:41

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('news', '0012_rename_is_clicked_recommendedarticle_is_liked_and_more'),
    ]

    operations = [
        migrations.AlterField(
            model_name='userdata',
            name='is_journalist',
            field=models.CharField(max_length=255),
        ),
    ]
