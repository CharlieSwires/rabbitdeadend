rabbit3
-------
<p>in git bash</p>
<p>git clone https://github.com/CharlieSwires/rabbitdeadend.git</p>
<p>This contains the java</p>

build
-----

<p>you'll need an application.properties file for the RabbitMQ settings and spring</p>
<p>mvn package</p>

<p>produces rabbit3-0.0.1-SNAPSHOT.jar in target</p>


deploy
------

<p>If there isn't one deployed and running you'll need rabbitmq.</p>
<p>docker pull rabbitmq:3.10-management</p>
<p>docker run --name rabbitmq --rm -it -p 15672:15672 -p 5672:5672 rabbitmq:3.10-management</p>
<br>
<p>docker build --tag rabbit3:latest .</p>
<p>docker run --name rabbit3 --link rabbitmq -d -p 9902:8080 rabbit3:latest</p>

