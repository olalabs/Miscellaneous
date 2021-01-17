package contacts.data;

import contacts.Contact;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class ContactRepositoryImpl implements ContactsFind {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Contact> findContacts(long max, int count) {
        String sql = "SELECT c FROM Contact c WHERE id > ? ORDER BY id ASC";
        TypedQuery<Contact> query = em.createQuery(sql, Contact.class).setParameter(1, max);
        return query.getResultList();
    }
}

