import RxJavaPractice.part13_1
import RxJavaPractice.part3_1
import RxJavaPractice.part3_2
import RxJavaPractice.part3_3
import RxJavaPractice.part3_4
import RxJavaPractice.part4_1
import RxJavaPractice.part4_2
import RxJavaPractice.part5_1
import RxJavaPractice.part6_asyn
import RxJavaPractice.part6_flowable
import RxJavaPractice.part6_syn
import RxJavaPractice.part7_1
import RxJavaPractice.part9_1
import RxJavaPractice.startPart2_2

fun main(args: Array<String>) {
//    RxJavaPractice.let {
//        part6_syn()
//    }
    testReified(3)

}

inline fun <T: Any> testReified(t: T){
    if(t.javaClass.name == "java.lang.Integer"){
        println("Integer")
    } else {
        println("not a nu,ber")
    }
}