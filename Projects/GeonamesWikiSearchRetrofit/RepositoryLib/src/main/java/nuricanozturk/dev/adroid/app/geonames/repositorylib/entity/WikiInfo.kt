package nuricanozturk.dev.adroid.app.geonames.repositorylib.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "wiki_info", indices = [Index("query")], foreignKeys = [ForeignKey(entity = QueryInfo::class, parentColumns = ["query"], childColumns = ["query"], onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)])
data class WikiInfo(@PrimaryKey(autoGenerate = true) var id : Long = 0L,
                    @ColumnInfo("query") var query : String = "",
                    @ColumnInfo("summary") var summary : String = "",
                    @ColumnInfo("title") var title : String = "",
                    @ColumnInfo("longitude") var longitude : Double = 0.0,
                    @ColumnInfo("latitude") var latitude : Double = 0.0,
                    @ColumnInfo("country_code") var countryCode : String? = null) : Serializable
{
    override fun toString() : String
    {
        return title
    }
}
