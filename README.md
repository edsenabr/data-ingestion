# data-ingestion

bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 10 --topic ingestion

bin/kafka-console-consumer.sh --topic ingestion --from-beginning --bootstrap-server localhost:9092

docker run --rm -e ADV_HOST=localhost -p 3030:3030 -p 8082:8082 -p 9092:9092 -p 2181:2181 -p 8081:8081 -e RUNNING_SAMPLEDATA=0 -e RUNTESTS=0 -e CONNECT_PORT=0 lensesio/fast-data-dev

docker run -d --name es762 -p 9200:9200 -e "discovery.type=single-node" elasticsearch:7.6.2

curl -X POST "http://localhost:9200/extrato/_search" -d'{"query":{"query_string":{"query": "8bfca9fa-ecdf-4f18-bfe3-e7f3c0360a40"}}}' -H 'Content-type:application/json'


curl -X POST "http://localhost:9200/extrato/_search" -d'{"query":{"query_string":{"query": "946eccbc-a87b-4aa5-b856-a13ca34ced98"}}}' -H 'Content-type:application/json'

docker run -d --name grafana -p 3000:3000 grafana/grafana

docker run -d --name prometheus -p 9090:9090 -v /home/capiau3/Desktop/Projetos/prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus --config.file=/etc/prometheus/prometheus.yml

ifconfig -a

curl -X POST "http://localhost:9200/extrato/_search?pretty"

bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group data-ingestion

curl localhost:8080/geraevento/10000/1000000