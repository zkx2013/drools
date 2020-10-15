package org.drools.match;

import org.drools.match.bean.Info;
import org.drools.match.bean.RuleParam;
import org.drools.template.parser.DefaultTemplateContainer;
import org.drools.template.parser.TemplateContainer;
import org.drools.template.parser.TemplateDataListener;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

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

    /**
     * debug 规则模版的转化
     * 根据文件创建模版容器
     * @throws FileNotFoundException
     */
    @Test
    public void testTemplateContainer() throws FileNotFoundException {
        FileInputStream templateStream = new FileInputStream("/Users/momo/Desktop/progrom/ideacode/drools/drools-examples/src/main/resources/org/drools/match/overTime.drt");
        TemplateContainer tc = new DefaultTemplateContainer( templateStream );
    }

    @Test
    public void testTemplateDataListener() throws FileNotFoundException {
        FileInputStream templateStream = new FileInputStream("/Users/momo/Desktop/progrom/ideacode/drools/drools-examples/src/main/resources/org/drools/match/overTime.drt");
        TemplateContainer tc = new DefaultTemplateContainer( templateStream );
        TemplateDataListener templateDataListener = new TemplateDataListener(2, 2, tc);
    }

}
