import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.jsonwebtoken.io.IOException
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Configuration
class JacksonConfiguration {
    @Bean
    @Primary
    fun serializingObjectMapper(): ObjectMapper {
        val objectMapper = ObjectMapper()
        val javaTimeModule = JavaTimeModule()
        javaTimeModule.addSerializer(LocalDate::class.java, LocalDateSerializer())
        javaTimeModule.addDeserializer(LocalDate::class.java, LocalDateDeserializer())
        objectMapper.registerModule(javaTimeModule)
        return objectMapper
    }

    class LocalDateSerializer : JsonSerializer<LocalDate?>() {
        override fun serialize(value: LocalDate?, gen: JsonGenerator?, serializers: SerializerProvider?) {
            gen?.writeString(value?.format(FORMATTER))
        }
    }

    class LocalDateDeserializer : JsonDeserializer<LocalDate?>() {
        override fun deserialize(p: JsonParser, ctxt: DeserializationContext?): LocalDate {
            return LocalDate.parse(p.getValueAsString(), FORMATTER)
        }
    }

    companion object {
        val FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    }
}