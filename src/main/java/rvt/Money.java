package rvt;

public class Money {

    private final int euros;
    private final int cents;

    public Money(int euros, int cents) {
        this.euros = euros;
        this.cents = cents;
    }

    public int euros() {
        return euros;
    }

    public int cents() {
        return cents;
    }

    public Money plus(Money addition) {
        int newEuros = this.euros + addition.euros;
        int newCents = this.cents + addition.cents;

        if (newCents >= 100) {
            newEuros += newCents / 100;
            newCents %= 100;
        }

        return new Money(newEuros, newCents);
    }

    public boolean lessThan(Money compared) {
        if (this.euros < compared.euros) {
            return true;
        } else if (this.euros == compared.euros) {
            return this.cents < compared.cents;
        }
        return false;
    }

    public Money minus(Money decreaser) {
        int newEuros = this.euros - decreaser.euros;
        int newCents = this.cents - decreaser.cents;

        if (newCents < 0) {
            newEuros -= 1;
            newCents += 100;
        }

        if (newEuros < 0) {
            newEuros = 0;
            newCents = 0;
        }

        return new Money(newEuros, newCents);
    }

    public String toString() {
        String zero = "";
        if (cents <= 10) {
            zero = "0";
        }

        return euros + "." + zero + cents + "e";
    }

    public static void main(String[] args) {
        Money a = new Money(10, 0);
        Money b = new Money(5, 0);

        Money c = a.plus(b);

        System.out.println(a);  // 10.00e
        System.out.println(b);  // 5.00e
        System.out.println(c);  // 15.00e

        a = a.plus(c);

        System.out.println(a);  // 25.00e
        System.out.println(b);  // 5.00e
        System.out.println(c);  // 15.00e

        Money d = new Money(10, 0);
        Money e = new Money(3, 0);
        Money f = new Money(5, 0);

        System.out.println(d.lessThan(e));  // false
        System.out.println(e.lessThan(f));  // true

        Money g = new Money(10, 0);
        Money h = new Money(3, 50);

        Money i = g.minus(h);

        System.out.println(g);  // 10.00e
        System.out.println(h);  // 3.50e
        System.out.println(i);  // 6.50e

        i = i.minus(g);

        System.out.println(g);  // 10.00e
        System.out.println(h);  // 3.50e
        System.out.println(i);  // 0.00e
    }
}

