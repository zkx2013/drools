package org.drools.match;

import org.drools.match.bean.Info;
import org.drools.match.bean.RuleParam;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class MatchRules {

    public static void main(String[] args) {
        KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
        KieSession ksession = kc.newKieSession("testRule");
        RuleParam ruleParam = new RuleParam();
        Info matchTeamInfo = new Info();
        matchTeamInfo.setGender(0);
        matchTeamInfo.setLang("ar");
        matchTeamInfo.setMode("");
        ksession.insert(ruleParam);
        ksession.insert(matchTeamInfo);
        ksession.fireAllRules();
        ksession.dispose();
    }
}
