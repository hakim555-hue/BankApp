package entities;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class AccountFacade {

    @PersistenceContext(unitName = "BankEntityWebApplicationPU")
    private EntityManager em;

    public void create(Account account) {
        em.persist(account);
    }

    public void edit(Account account) {
        em.merge(account);
    }

    public void remove(Account account) {
        em.remove(em.merge(account));
    }

    public Account find(Object id) {
        return em.find(Account.class, id);
    }

    public List<Account> findAll() {
        return em.createQuery("SELECT a FROM Account a", Account.class)
                .getResultList();
    }
}
