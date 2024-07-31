package org.dbconverter.querysender.mysql.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Lob
import jakarta.persistence.Table
import org.dbconverter.querysender.h2.entity.H2Wine

@Entity
@Table(name = "WINE")
class MySqlWine(

    @Column(name = "name", length = 100, unique = true)
    val name: String,

    @Column(name = "sweetness")
    val sweetness: Int,

    @Column(name = "acidity")
    val acidity: Int,

    @Column(name = "body")
    val body: Int,

    @Column(name = "tannin")
    val tannin: Int,

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    val wineType: WineType,

    @Column(name = "aroma")
    val aroma: String,

    @Column(name = "price")
    var price: Int?,

    @Column(name = "kind", length = 50)
    val kind: String?,

    @Column(name = "style", length = 50)
    val style: String?,

    @Column(name = "country", length = 50)
    val country: String?,

    @Column(name = "region", length = 50)
    val region: String?,

    @Lob
    @Column(name = "embedding")
    val embedding: String?,

    @Id //@GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
){
    companion object{
        fun toMySql(h2WineList:List<H2Wine>):List<MySqlWine>{
            return h2WineList.map {
                MySqlWine(
                    name=it.name,
                    sweetness=it.sweetness,
                    acidity=it.acidity,
                    body=it.body,
                    tannin=it.tannin,
                    wineType=it.wineType,
                    aroma=it.aroma,
                    price=it.price,
                    kind=it.kind,
                    style=it.style,
                    country=it.country,
                    region=it.region,
                    embedding=it.embedding,
                    id=it.id
                )
            }
        }
    }
}