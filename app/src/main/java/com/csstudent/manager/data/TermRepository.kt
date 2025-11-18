package com.csstudent.manager.data

import com.csstudent.manager.database.TermDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TermRepository(private val termDao: TermDao) {

    fun getAllTerms(): Flow<List<Term>> = termDao.getAllTerms()

    fun getTermsByCategory(category: String): Flow<List<Term>> {
        return if (category == TermCategory.ALL.displayName) {
            termDao.getAllTerms()
        } else {
            termDao.getTermsByCategory(category)
        }
    }

    fun searchTerms(query: String, category: String = TermCategory.ALL.displayName): Flow<List<Term>> {
        return if (query.isEmpty()) {
            getTermsByCategory(category)
        } else {
            termDao.searchTerms(query).map { terms ->
                if (category == TermCategory.ALL.displayName) {
                    terms
                } else {
                    terms.filter { it.category == category }
                }
            }
        }
    }

    suspend fun addTerm(term: Term) {
        termDao.insert(term)
    }

    suspend fun deleteTerm(termId: String) {
        termDao.deleteById(termId)
    }

    suspend fun getTermById(id: String): Term? {
        return termDao.getTermById(id)
    }
}
