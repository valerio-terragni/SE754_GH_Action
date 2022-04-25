package example.bank;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;

class UserTest {

    @Test
    public void withdraw_should_fail_not_enough_balance() {
        BankAccount bank = Mockito.mock(BankAccount.class);
        Mockito.when(bank.getBalance()).thenReturn(100);
        User account = new User(bank);
        boolean result = account.withdraw(200);
        assertFalse(result);

    }

    @Test
    public void withdraw_should_succeed_enough_balance() {
        BankAccount bank = Mockito.mock(BankAccount.class);
        Mockito.when(bank.getBalance()).thenReturn(1000);
        User account = new User(bank);
        boolean result = account.withdraw(200);
        assertTrue(result);
        Mockito.verify(bank, times(1)).setBalance(800);
    }

    @Test
    public void withdraw_should_succeed_enough_balance_capture() {
        BankAccount bank = Mockito.mock(BankAccount.class);
        Mockito.when(bank.getBalance()).thenReturn(1000);
        ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
        User account = new User(bank);
        boolean result = account.withdraw(200);
        assertTrue(result);
        Mockito.verify(bank).setBalance(argumentCaptor.capture());
        assertEquals(800,argumentCaptor.getValue());
    }

}