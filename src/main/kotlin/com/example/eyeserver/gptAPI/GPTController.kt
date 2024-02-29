package com.example.eyeserver.gptAPI

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class GPTController (){

    @GetMapping("/gpt")
    fun gpt() {

        val key = "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"
        //val system = "응답은 한국어로만 해. 질문에 대한 언급은 제외하고 대답만 해."
        val system = "Answer in Korean only. You have to answer the requested questions with a spaces separated list of max 50. Exclude mentioning the questions."
        val request = ChatBot.ChatCompletionRequest("gpt-3.5-turbo", system)
        val bot = CachedChatBot(key, request)
        val prompt = "Please make a professional information non-literature text that includes about 20 Korean sentences that are difficult to read until you are in your 20s."
        //val prompt = "Tell me what kind of Pokemon you have"
        //val prompt = "Make a difficult-to-read paragraph for adults in their 20s, consisting of up to 50 lists separated by spaces."

        println("How will you respond?")
        val response = bot.generateResponse(prompt)
        println("\t$response")



        println()
        println()
        println()



        //val question = "$response Make multiple choice questions and answers to the previous content"
        val question = "$response \n\n Make multiple-choice questions and answers to the preceding content. Make 3 questions and 4 multiple-choice views exactly. Write the answers in the form \"Answer: 1, 2, 3\" at the end."
        val res = bot.generateResponse(question)
        println("\t$res")

    }
}