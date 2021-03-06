package org.ricone.api.core.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 1.2.1
 * @since 2018-11-05
 */


@Entity
@Table(name = "eventloglea")
public class LeaEventLog extends EventLog implements Serializable {
    private static final long serialVersionUID = 2954727154531497019L;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name="ObjectRefId", referencedColumnName="leaRefId", nullable = false),
            @JoinColumn(name="ObjectSchoolYear", referencedColumnName="leaSchoolYear", nullable = false)
    })
    private Lea lea;*/

    public LeaEventLog() {
    }

}
