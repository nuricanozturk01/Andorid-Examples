package nuricanozturk.dev.adroid.app.geonames.repositorylib.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable
import java.time.LocalDateTime
import java.time.LocalDateTime.now

@Entity(tableName = "query_info", indices = [Index(value = ["query"], unique = true)])
data class QueryInfo(@PrimaryKey(autoGenerate = true) var id : Long = 0L,
                     @ColumnInfo("query") var query : String = "",
                     @ColumnInfo("query_count") var queryCount : Int = 1,
                     @ColumnInfo("last_query_time") var lastQueryTime : LocalDateTime = now()) :
    Serializable
{

}
