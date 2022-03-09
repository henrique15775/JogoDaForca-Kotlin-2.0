package model
import java.util.Scanner
class JogodaForca {
    var dica: String
    private var palavra: String
    var resultado: Int
    var qnt_erros: Int
    private var lista_descoberto= mutableListOf<String>()
    constructor(palavra: String, dica: String){
        this.palavra = palavra.split("").joinToString().replace(",", "").replace("/0", "").replace(" ", "").uppercase()
        this.dica = dica
        this.resultado = 0
        this.qnt_erros = 0

    }

    /*
    fun ObterPalavraDica(): PalavraDica {
        //val arrayOfStrings = arrayOf("Casa", "Dica")
        val palavra = PalavraDica("Casa", "Dica:Nome da construção em que você reside")
        return palavra
    }*/


    fun retornarQuantidadeDeLetrasDistintas(): Int {
        val new_vet = mutableListOf<String>()
        for (word in this.palavra) {
            if (word.toString().uppercase() != "/0" && word.toString().uppercase() != "") {
                new_vet.add(word.toString())

            }
        }
        return new_vet.distinct().size
    }


    fun letrasDescobertas(letras_reveladas: List<String>): String {


        var str_words = this.palavra
        for (word in str_words) {
            var confirm = false
            for (word_revealed in letras_reveladas) {
                if (word.toString().uppercase() == word_revealed.uppercase()) {
                    //println("ENTROU AQ")
                    confirm = true
                    break
                }
            }
            if (confirm == false) {
                str_words = str_words.replace(word, '*')
            }
        }
        return str_words
    }

    fun adivinharPalavra(adivinhar: String?){

        println(letrasDescobertas(this.lista_descoberto))
        print("-> ")
        //var acertou = false
        //var adivinhar = readLine()

        if (adivinhar != null) {
            for (word in adivinhar) {
                if (this.palavra.contains(
                        word.toString().uppercase()
                    ) && this.lista_descoberto.contains(word.toString().uppercase()) == false
                ) {
//                    println("Palavra ...")
                    this.lista_descoberto.add(word.toString())
                    println(this.lista_descoberto.toString())
          //          acertou = true
                } else {
                    this.qnt_erros = this.qnt_erros -1
                }
            }
        }
        /*if (acertou == false) {
            qnt_erros = qnt_erros - 1
        }*/


    }

    fun verificaResultado(): Int{
        if (!letrasDescobertas(this.lista_descoberto).contains("*")) {
            println(letrasDescobertas(this.lista_descoberto))
            println("VOCE GANHOUUU XD")
            this.resultado = 1
            return 1
        }

        if (qnt_erros <= -6) {
            //println(letrasDescobertas(lista_descoberto))
            println("VOCE PERDEUUUUUU :(")
            this.resultado = -1
            return -1
        }
        return 0
    }

    fun IniciarJogo() {

        try {

            //var pal = ObterPalavraDica()

            val delim = ""
            //val list = pal.palavra.split(delim)
            //var palavraSorteada = list.joinToString().replace(",", "").replace("/0", "").replace(" ", "").uppercase()
            while (true) {
                println(
                    "Jogo da Forca! \nTentativas restantes:${qnt_erros + 6}\n Qnt. de letras: ${this.palavra.length} \nLetras Distintas: ${
                        retornarQuantidadeDeLetrasDistintas()
                    }\n${this.dica}"
                )



                println(letrasDescobertas(lista_descoberto))
                print("-> ")
                var adivinhar = readLine()
                adivinharPalavra(adivinhar)
                if(verificaResultado() == 1 || verificaResultado() == -1){
                    break
                }
            }
        } catch (e: Exception) {
            println(e)
        }
    }

}

/*
*
* fun ObterPalavraDica(){} -  Done
* fun retornarQuantidadeDeLetras(){} - Done
* fun retornarQuantidadeDeLetrasDistintas(){} - Done
* fun VerificarLetraInPalavra(){} - Done
* fun letrasDescobertas(){} - Done
* fun retornarStatusDaPalavra(){} - Done
* fun Gerenciar Quantidade de tentativas - Done
*
*
* */