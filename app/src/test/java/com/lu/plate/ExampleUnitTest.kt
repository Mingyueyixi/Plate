package com.lu.plate

import com.blankj.utilcode.util.GsonUtils
import com.google.gson.*
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import com.lu.plate.data.Content
import com.lu.plate.util.GsonUtil
import com.lu.plate.util.JsonObjectKotlin
import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.reflect.Type

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun transferGsonOrgJson() {
        var gson = GsonBuilder()
            .registerTypeAdapter(JSONObject::class.java, object : JsonSerializer<JSONObject> {
                override fun serialize(
                    src: JSONObject?,
                    typeOfSrc: Type?,
                    context: JsonSerializationContext?
                ): JsonElement {

                    return context?.serialize(
                        GsonUtil.fromJson(
                            src.toString(),
                            JsonObject::class.java
                        )
                    )
                        ?: JsonNull.INSTANCE
                }

            })
            .registerTypeAdapter(JSONObject::class.java, object : JsonDeserializer<JSONObject> {
                override fun deserialize(
                    json: JsonElement?,
                    typeOfT: Type?,
                    context: JsonDeserializationContext?
                ): JSONObject {
                    return JSONObject(json.toString())
                }

            })
            .setPrettyPrinting()
            .create()

        val data = Content(
            props = JsonObjectKotlin.from(
                """{
                 "click":"nima"   
                }""".trimMargin()
            )
        )

        var content = gson.fromJson(
            """
            {
              "templateId": -1,
              "id": 0,
              "props": {
                "map": {
                  "click": "nima"
                }
              }
            }
        """.trimIndent(), Content::class.java
        )
        println(content)
        print(GsonUtil.getPrettyGson().toJson(data))


        var orgJson = gson.fromJson(
            """
            {
              "templateId": -1,
              "id": 0,
              "props": {
                "click": "nima"
              }
            }
        """, JSONObject::class.java
        )

        print(orgJson)

    }
}



