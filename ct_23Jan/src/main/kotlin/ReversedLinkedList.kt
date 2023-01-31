class ReversedLinkedList: Solution {
    override fun start() {
        val result = reverseList(null)
        println(result)

        val elem3 = ListNode(3).apply { next = null }
        val elem2 = ListNode(2).apply { next = elem3 }
        val elem1 = ListNode(1).apply { next = elem2 }

        val result1 = reverseList(
            elem1
        )
        println(result1)
    }

    /**
     * Example:
     * var li = ListNode(5)
     * var v = li.`val`
     * Definition for singly-linked list.
     * class ListNode(var `val`: Int) {
     *     var next: ListNode? = null
     * }
     */
    fun reverseList(head: ListNode?): ListNode? {
        if (head?.next == null) return head

        var leftNode: ListNode? = null
        var midNode = head
        while (midNode != null) {
            var rightNode = midNode.next

            // 작업
            midNode.next = leftNode

            //갱신
            leftNode = midNode
            midNode = rightNode
        }
        return leftNode
    }
}

data class ListNode(var value: Int) {
    var next: ListNode? = null
}