package com.example.eyeserver.gptAPI

/**
 * Caches the initial request to make interactions easier (and String based).
 */
class CachedChatBot(apiKey: String, public val request: ChatCompletionRequest) : ChatBot(apiKey) {

    fun generateResponse(content: String, role: String = "user"): String {
        request.messages.add(ChatMessage(role, content))
        val response = super.generateResponse(request)
        val temp = response.choices[0].message
        request.messages.add(temp)
        return temp.content
    }
}
