package org.akanza.repository;

import org.akanza.model.SmsSent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Christian Amani on 02/05/2017.
 */
@Repository
public interface SmsSentRepository extends JpaRepository<SmsSent,Long>
{
}
