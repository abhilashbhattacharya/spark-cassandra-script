# spark-cassandra-script
This script helps load selective partitions and reset TTL for them in bulk.

SparkCassandraScriptIncremental:
The script was used to chery pick selected bloated partitions in cassandra and reset the ttl incremently to delete them over a time period without putting much stress on the cluster. This also helped in limiting the number of tombstones created at a go and thus reduced load on compaction.

SparkCassandraScript:
The script sets a minimal ttl of 100 to all the selected records.
