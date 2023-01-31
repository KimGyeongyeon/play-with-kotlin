import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Action
import io.reactivex.rxjava3.observables.ConnectableObservable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Thread.currentThread
import java.lang.Thread.sleep
import java.util.concurrent.TimeUnit

object RxJavaPractice {
    val printerObserver = object : Observer<Int> {
        override fun onSubscribe(d: Disposable) {
            println("--onSubscribe--")
        }

        override fun onError(e: Throwable) {
            println("--Error--")
        }

        override fun onComplete() {
            println("--Complete--")
        }

        override fun onNext(t: Int) {
            println("onNext : $t")
        }

    }

    val singleObserver = object : SingleObserver<Int> {
        override fun onSubscribe(d: Disposable) {
            println("onSubscribe")
        }

        override fun onError(e: Throwable) {
            println("--Error--")
        }

        override fun onSuccess(t: Int) {
            println("onSuccess : $t")
        }

    }

    fun startPart2_1() {
        val observable: Observable<Int> = Observable.just(1, 2)
        observable.subscribe(printerObserver)
    }

    fun startPart2_2() {
        val list = listOf<Int>(1, 2, 3, 4)
        val obs: Observable<Int> = Observable.fromIterable(list)
        obs.subscribe(printerObserver)
    }

    fun part3_1() {
        val obs = Observable.range(-1, 10)
        obs.subscribe(printerObserver)
    }

    fun part3_2() {
        //val disposer = CompositeDisposable()
        val disposable = Observable
            .interval(1, TimeUnit.SECONDS)
            .subscribe { item -> println(item) }
        val waitForInput1 = readLine()
        disposable.dispose()
        val waitForInput2 = readLine()
    }

    fun part3_3() {
        val obs = Observable.timer(1L, TimeUnit.SECONDS)
        obs.subscribe { item -> println("timer $item") }
        val waitForInput1 = readLine()
    }

    fun part3_4() {
        val action = Action {
            println("Action executed")
        }
        val comp = Completable.fromAction(action)
        comp.subscribe { println("Action completed") }
    }

    fun part4_1() {
        val input = readLine()

        /* emitter를 사용해서 예외처리 가능 */
        val single: Single<Int> = Single.create { emitter ->
            if (input.isNullOrBlank()) {
                emitter.onError(Exception("input is $input"))
            } else {
                /* onNext 대신 onSuccess로 item을 넘긴다. */
                emitter.onSuccess(input.length)
            }
        }

        /* Observer 사용 불가 */
        single.subscribe(singleObserver)
    }

    fun part4_2() {
        val input = readLine()
        val maybe = Maybe.create<Int> { emitter ->
            if (input.isNullOrBlank()) {
                emitter.onError(Exception("input is $input"))
            } else if (input == "quit") {
                emitter.onComplete()
            } else {
                emitter.onSuccess(input.length)
            }
        }
        maybe.subscribe(
            object : MaybeObserver<Int> {
                override fun onSubscribe(d: Disposable) {
                    println("onSubscribe")
                }

                override fun onError(e: Throwable) {
                    println("onError $e")
                }

                override fun onComplete() {
                    println("onComplete")
                }

                override fun onSuccess(t: Int) {
                    println("onSuccess : $t")
                }
            }
        )
        val wait = readLine()
    }

    fun part5_1() {
        val completableObserver = object : CompletableObserver {
            override fun onSubscribe(d: Disposable) {
                println("onSubscribe")
            }

            override fun onComplete() {
                println("onComplete")
            }

            override fun onError(e: Throwable) {
                println("onError $e")
            }
        }

        /* 둘다 람다로 변환 가능하다. */
        val comp1 = Completable.create(object : CompletableOnSubscribe {
            override fun subscribe(emitter: CompletableEmitter) {
                // emitter.onComplete()
                //emitter.onError()
            }
        })

        val comp2 = Completable.fromAction(object : Action {
            override fun run() {
                // subscribe 이후에 실행되는 동작
                println("ex : Delete item from DB")
            }
        })

        comp1.subscribe(completableObserver)

        val wait = readLine()
    }

    fun part6_syn() {
        Observable.range(1, 10)
            .subscribeOn(Schedulers.io())
            .map { num -> num.apply { println("emit $num") } }
            .subscribe {
                sleep(1000L)
                println("receive $it")
            }
        readln()
    }

    fun part6_asyn() {
        Observable.range(1, 10)
            .subscribeOn(Schedulers.newThread())
            .map { num ->
                num.apply {
                    println("emit $num on ${Thread.currentThread().id}")
                }
            }
            .observeOn(Schedulers.io())
            .subscribe {
                sleep(1000L)
                println("receive $it on ${Thread.currentThread().id}")
            }
        readln() // 백그라운드 연산이 계속 돌아가도록 함
    }

    fun part6_flowable() {
        Flowable
            .range(1, 10000)
            .subscribeOn(Schedulers.newThread())
            .map { num ->
                num.apply {
                    println("emit $num on ${Thread.currentThread().id}")
                }
            }
            .observeOn(Schedulers.io())
            .subscribe {
                sleep(1000L)
                println("receive $it on ${Thread.currentThread().id}")
            }
        readln() // 백그라운드 연산이 계속 돌아가도록 함
    }

    fun part7_1() {
        val hot: ConnectableObservable<Long> = Observable.interval(1, TimeUnit.SECONDS).publish()
        hot.connect()

        hot.subscribe { println("[observer 1] data : $it") }
        readln()

        hot.subscribe { println("[observer 2] data : $it") }
        readln()
    }

    fun part9_1() {
        Observable.just(1,2,3,2,2,2,3,3,3)
            .distinct()
            .defaultIfEmpty(-1)
            .map { it.toString() }
            .subscribe{
                println(it)
            }
    }

    fun part13_1() {
        Observable.just(1,2,3,4)
            .subscribeOn(Schedulers.io())
            .doOnNext{ println("item $it thread : ${currentThread().name}")}
            .observeOn(Schedulers.computation())
            .subscribe({
                println("item $it thread : ${currentThread().name}")
            })
        readln()
        /**
         * subscribeOn(A) Only : upstream A downstream A
         * observeOn(B) Only : upstream - downstream B
         * both : upstream A downstream B
         * */
    }
}