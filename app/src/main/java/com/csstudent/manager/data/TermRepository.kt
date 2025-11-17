package com.csstudent.manager.data

object TermRepository {
    private val terms = mutableListOf<Term>()

    init {
        // 초기 데이터
        addDefaultTerms()
    }

    private fun addDefaultTerms() {
        terms.addAll(
            listOf(
                Term(
                    term = "알고리즘",
                    definition = "문제를 해결하기 위한 절차나 방법. 컴퓨터가 어떤 일을 수행하기 위한 단계적 방법",
                    category = TermCategory.ALGORITHM.displayName
                ),
                Term(
                    term = "자료구조",
                    definition = "데이터를 효율적으로 저장하고 관리하기 위한 구조. 배열, 리스트, 트리, 그래프 등",
                    category = TermCategory.DATA_STRUCTURE.displayName
                ),
                Term(
                    term = "스택",
                    definition = "후입선출(LIFO) 구조의 자료구조. 가장 나중에 들어온 데이터가 가장 먼저 나간다",
                    category = TermCategory.DATA_STRUCTURE.displayName
                ),
                Term(
                    term = "큐",
                    definition = "선입선출(FIFO) 구조의 자료구조. 가장 먼저 들어온 데이터가 가장 먼저 나간다",
                    category = TermCategory.DATA_STRUCTURE.displayName
                ),
                Term(
                    term = "TCP/IP",
                    definition = "인터넷에서 컴퓨터들이 서로 정보를 주고받는데 쓰이는 통신 프로토콜의 모음",
                    category = TermCategory.NETWORK.displayName
                ),
                Term(
                    term = "HTTP",
                    definition = "웹에서 데이터를 주고받기 위한 프로토콜. 클라이언트와 서버 간의 통신 규약",
                    category = TermCategory.NETWORK.displayName
                ),
                Term(
                    term = "프로세스",
                    definition = "실행 중인 프로그램. 운영체제로부터 자원을 할당받아 동작하는 실행 단위",
                    category = TermCategory.OS.displayName
                ),
                Term(
                    term = "쓰레드",
                    definition = "프로세스 내에서 실행되는 흐름의 단위. 프로세스의 자원을 공유하며 실행된다",
                    category = TermCategory.OS.displayName
                ),
                Term(
                    term = "SQL",
                    definition = "관계형 데이터베이스 관리 시스템(RDBMS)의 데이터를 관리하기 위해 설계된 특수 목적의 프로그래밍 언어",
                    category = TermCategory.DATABASE.displayName
                ),
                Term(
                    term = "정규화",
                    definition = "데이터베이스 설계에서 중복을 최소화하고 무결성을 보장하기 위해 데이터를 구조화하는 프로세스",
                    category = TermCategory.DATABASE.displayName
                ),
                Term(
                    term = "객체지향 프로그래밍",
                    definition = "프로그램을 객체들의 모임으로 파악하고자 하는 프로그래밍 패러다임. 캡슐화, 상속, 다형성이 특징",
                    category = TermCategory.PROGRAMMING.displayName
                ),
                Term(
                    term = "API",
                    definition = "Application Programming Interface. 응용 프로그램에서 사용할 수 있도록 운영체제나 프로그래밍 언어가 제공하는 기능을 제어할 수 있게 만든 인터페이스",
                    category = TermCategory.PROGRAMMING.displayName
                ),
                Term(
                    term = "REST API",
                    definition = "HTTP를 기반으로 필요한 자원에 접근하는 방식을 정해놓은 아키텍처",
                    category = TermCategory.WEB.displayName
                ),
                Term(
                    term = "Git",
                    definition = "분산 버전 관리 시스템. 소스 코드의 변경 이력을 추적하고 관리하는 도구",
                    category = TermCategory.ETC.displayName
                )
            )
        )
    }

    fun getAllTerms(): List<Term> = terms.toList()

    fun getTermsByCategory(category: String): List<Term> {
        return if (category == TermCategory.ALL.displayName) {
            terms.toList()
        } else {
            terms.filter { it.category == category }
        }
    }

    fun searchTerms(query: String): List<Term> {
        return terms.filter {
            it.term.contains(query, ignoreCase = true) ||
                    it.definition.contains(query, ignoreCase = true)
        }
    }

    fun addTerm(term: Term) {
        terms.add(term)
    }

    fun deleteTerm(termId: String) {
        terms.removeIf { it.id == termId }
    }
}
