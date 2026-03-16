package my.presentation;

import entities.Account;
import entities.AccountFacade;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named(value = "accountInfoView")
@SessionScoped
public class AccountInfoView implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private AccountFacade accountFacade;

    private String clientName;
    private double balance;
    private List<Account> accounts;

    public String createAccount() {
        Account newAccount = new Account(clientName, balance);
        accountFacade.create(newAccount);
        loadAccounts();
        clientName = "";
        balance = 0;
        return null;
    }

    public void loadAccounts() {
        accounts = accountFacade.findAll();
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Account> getAccounts() {
        if (accounts == null) {
            loadAccounts();
        }
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
