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
public class JPAContactRepository implements ContactRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Contact> findContacts(long max, int count) {
        String sql = "SELECT c FROM Contact c WHERE id > ? ORDER BY id ASC";
        TypedQuery<Contact> query = em.createQuery(sql, Contact.class).setParameter(1, max);
        return query.getResultList();
    }

    @Override
    public Contact findOne(long id) {
        return em.find(Contact.class, id);
    }

    @Override
    public void insert(Contact contact) {
        em.persist(contact);
    }

    @Override
    public void update(Contact contact) {
        em.merge(contact);
    }

    @Override
    public void delete(long id) {
        Contact record = findOne(id);
        em.remove(record);
    }
}
