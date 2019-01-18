import com.datastax.spark.connector.writer._
import org.apache.spark.sql.functions._
implicit val rowWriter = SqlWriter.Factory

val df = sqlContext
  .read
  .format("org.apache.spark.sql.cassandra")
  .options(Map( "table" -> "sample", "keyspace" -> "test"))
  .load
  .where(col("col1")==="samplevalue")

  val rows = df.rdd
  
 rows.saveToCassandra("keyspace", "table", writeConf = WriteConf(ttl = TTLOption.constant(100)))