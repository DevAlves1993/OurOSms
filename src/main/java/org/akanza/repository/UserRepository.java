package org.akanza.repository;

import org.akanza.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Christian Amani on 01/05/2017.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long>
{
    User findByLogin(String login);
}
