FROM khipu/openjdk8-alpine:latest

COPY apache-activemq-5.15.10-bin.tar.gz activemq.tar.gz

RUN tar -xzf activemq.tar.gz

EXPOSE 8161

CMD ["apache-activemq-5.15.10/bin/activemq", "console"]