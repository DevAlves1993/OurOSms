package org.akanza.repository;

import org.akanza.model.SMS;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Christian Amani on 02/05/2017.
 */
public interface SMSRepository extends JpaRepository<SMS,Long>
{
}
