/**
 * 
 */
package br.com.caelum.leilao.matcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

/**
 * @author Ramon Vieira
 *
 */
public class LeilaoMatcher extends TypeSafeMatcher<Leilao> {
	
	private final Lance lance;

    public LeilaoMatcher(Lance lance) {
        this.lance = lance;
    }

	/* (non-Javadoc)
	 * @see org.hamcrest.SelfDescribing#describeTo(org.hamcrest.Description)
	 */
	@Override
	public void describeTo(Description description) {
        description.appendText("leilao com lance " + lance.getValor());
    }

	/* (non-Javadoc)
	 * @see org.hamcrest.TypeSafeMatcher#matchesSafely(java.lang.Object)
	 */
	@Override
    protected boolean matchesSafely(Leilao item) {
        return item.getLances().contains(lance);
    }
	
	public static Matcher<Leilao> temUmLance(Lance lance) {
        return new LeilaoMatcher(lance);
    }

}
