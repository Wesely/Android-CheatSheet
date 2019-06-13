//Gist : https://gist.github.com/Wesely/ef2ca681a7795802edefdd138db38c3c

abstract class ExampleAbstract(val zero: Int =0) {
    companion object {
        const val one = 1
        var two = 2
    }
    abstract var num :Int?
    val three = 3
    var four = 4
    fun getTheOne() = one
    open fun getTheTwo(): Int { return two }
    final fun getTheThree() = 3 // default final, redundant
    abstract fun getTheFour(): Int
}

class TryAbstract:ExampleAbstract(/*zero*/){
    override var num: Int?
        get() = 0
        set(value) {}

    // Can override or not
    override fun getTheTwo(): Int {
        return super.getTheTwo()
    }

    override fun getTheFour() = 4

}

interface ExampleInterface { // interface may not have a constructor
    companion object {
        const val one = 1
        var two = 2
    }
    abstract var num :Int?
    val three = 3 // Error, property initializers are not allowed
    var four = 4  // Error, property initializers are not allowed
    fun getTheOne() = one
    open fun getTheTwo(): Int { return two }  // default open, open is redundant
    final fun getTheThree() = 3             // Error, final is not allowed in interface
    abstract fun getTheFour(): Int          // default open, abstract is redundant
}

class TryInterface(override var num: Int?) :ExampleInterface{
    // Can override, or not
    override fun getTheOne(): Int { //
        return super.getTheOne()
    }

    // MUST override
    override fun getTheFour(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}