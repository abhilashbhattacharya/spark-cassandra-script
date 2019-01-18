# spark-cassandra-script
This script helps load selective partitions and reset TTL for them in bulk.

The script was used to chery pick selected bloated partitions in cassandra and reset the ttl incremently to delete them over a time period without putting much stress on the cluster.
