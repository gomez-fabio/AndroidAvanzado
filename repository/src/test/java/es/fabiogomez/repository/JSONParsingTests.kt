package es.fabiogomez.repository

import es.fabiogomez.repository.model.ShopEntity
import es.fabiogomez.repository.network.json.JsonEntitiesParser
import es.fabiogomez.repository.util.ReadJsonFile
import org.junit.Assert.*
import org.junit.Test

class JSONParsingTests {
    @Test
    @Throws(Exception::class)
    fun given_valid_string_containing_json_it_parses_correctly() {

        val shopsJson = ReadJsonFile().loadJSONFromAsset("shops.json")

        assertTrue(shopsJson.isNotEmpty())

        // parsing
        val parser = JsonEntitiesParser()
        val shop = parser.parse<ShopEntity>(shopsJson)

        assertNotNull(shop)
        assertEquals("Cortefiel - Preciados",shop.name)
    }

}