package edu.wccnet.sepolidori.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import edu.wccnet.sepolidori.entity.Invoice;

@Repository
public class InvoiceDAOImpl implements InvoiceDAO {
	
	private final SessionFactory sessionFactory;
	
	public InvoiceDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Invoice getInvoice(int invoiceId) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Invoice.class, invoiceId);
	}

	@Override
	public List<Invoice> getInvoices() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("From Invoice", Invoice.class).getResultList();
	}

	@Override
	public List<Invoice> getInvoices(int customerId) {
		Session session = sessionFactory.getCurrentSession();
		Query<Invoice> query = session.createQuery("FROM Invoice i WHERE i.customer.id = :customerId", Invoice.class);
		query.setParameter("customerId", customerId);
		return query.getResultList();
	}

	@Override
	public void saveInvoice(Invoice invoice) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(invoice);
	}

}
