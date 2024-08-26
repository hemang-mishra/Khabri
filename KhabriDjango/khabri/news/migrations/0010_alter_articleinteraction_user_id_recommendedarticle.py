# Generated by Django 5.0.6 on 2024-08-25 22:49

import django.db.models.deletion
from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('news', '0009_userdata'),
    ]

    operations = [
        migrations.AlterField(
            model_name='articleinteraction',
            name='user_id',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='user_data_id', to='news.userdata'),
        ),
        migrations.CreateModel(
            name='RecommendedArticle',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('is_clicked', models.BooleanField(default=False)),
                ('news', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='news.news')),
                ('user', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='news.userdata')),
            ],
        ),
    ]
