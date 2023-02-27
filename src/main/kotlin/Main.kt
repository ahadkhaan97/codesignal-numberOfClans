fun main() {
    print(solution(mutableListOf(2, 3), 6))
}

class UnionFind(n: Int) {
    private val parent = IntArray(n) { it }
    private val size = IntArray(n) { 1 }

    fun find(x: Int): Int {
        if (parent[x] != x) {
            parent[x] = find(parent[x])
        }
        return parent[x]
    }

    fun union(x: Int, y: Int) {
        val rootX = find(x)
        val rootY = find(y)
        if (rootX != rootY) {
            if (size[rootX] < size[rootY]) {
                parent[rootX] = rootY
                size[rootY] += size[rootX]
            } else {
                parent[rootY] = rootX
                size[rootX] += size[rootY]
            }
        }
    }

    fun getNumSets(): Int {
        return parent.indices.filter { parent[it] == it }.count()
    }
}

fun solution(divisors: List<Int>, k: Int): Int {
    val uf = UnionFind(k + 1)
    for (i in 1..k) {
        for (j in (i + i)..k step i) {
            if (divisors.all { d -> (i % d == 0) == (j % d == 0) }) {
                uf.union(i, j)
            }
        }
    }
    return uf.getNumSets()-1
}
