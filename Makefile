run:
	rm nohup.out
	nohup mvn spring-boot:run &

test:
	mvn spring-boot:run

kill:
	kill -9 $$(lsof -i tcp:8090 -t)