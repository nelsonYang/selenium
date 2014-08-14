package com.freeborders.base.enumeration;

/**
 *
 * @author nelson.yang
 */
public enum ModelNameEnum {
        HOME{
            @Override
            public String toString(){
                return "Home";
            }
        },
        CASES{
            @Override
            public String toString(){
                return "Cases";
            }
        
        },
        TOOLS{
            @Override
            public String toString(){
                return "Tools";
            }
        
        },
        BILLING{
           @Override
            public String toString(){
                return "Billing";
            }
        },
        REPORTS{
            @Override
            public String toString(){
                return "Reports";
            }
        },
        ADMINISTRATION{
            @Override
            public String toString(){
                return "Administration";
            }
        }
}
