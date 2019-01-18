import com.datastax.spark.connector.writer._
import org.apache.spark.sql.functions._
implicit val rowWriter = SqlWriter.Factory

val df = sqlContext
  .read
  .format("org.apache.spark.sql.cassandra")
  .options(Map( "table" -> "sample", "keyspace" -> "test"))
  .load
  .where(col("col1")==="samplevalue")
  
  var increment = 100
  
  val size = df.count()
  if(size>25){
  increment = 86400/(size%25) //This increment will make sure the program deletes 4% of data everyday thus reducing the no of tombstones created on a particular day
  }
  val newDf = df.withColumn("ttl", monotonicallyIncreasingId.multiply(increment))
  val rows = newDf.rdd
  
 rows.saveToCassandra("keyspace", "table", writeConf = WriteConf(ttl = TTLOption.perRow("ttl")