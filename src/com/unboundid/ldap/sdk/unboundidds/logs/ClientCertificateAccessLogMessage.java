/*
 * Copyright 2009-2016 UnboundID Corp.
 * All Rights Reserved.
 */
/*
 * Copyright (C) 2015-2016 UnboundID Corp.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPLv2 only)
 * or the terms of the GNU Lesser General Public License (LGPLv2.1 only)
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see <http://www.gnu.org/licenses>.
 */
package com.unboundid.ldap.sdk.unboundidds.logs;



import com.unboundid.util.NotMutable;
import com.unboundid.util.ThreadSafety;
import com.unboundid.util.ThreadSafetyLevel;



/**
 * This class provides a data structure that holds information about a log
 * message that may appear in the Directory Server access log about a client
 * certificate that has been presented to the server.
 * <BR>
 * <BLOCKQUOTE>
 *   <B>NOTE:</B>  This class is part of the Commercial Edition of the UnboundID
 *   LDAP SDK for Java.  It is not available for use in applications that
 *   include only the Standard Edition of the LDAP SDK, and is not supported for
 *   use in conjunction with non-UnboundID products.
 * </BLOCKQUOTE>
 */
@NotMutable()
@ThreadSafety(level=ThreadSafetyLevel.COMPLETELY_THREADSAFE)
public final class ClientCertificateAccessLogMessage
       extends AccessLogMessage
{
  /**
   * The serial version UID for this serializable class.
   */
  private static final long serialVersionUID = -2585979292882352926L;



  // The subject DN for the issuer certificate.
  private final String issuerSubject;

  // The subject DN for the client certificate.
  private final String peerSubject;



  /**
   * Creates a new client certificate access log message from the provided
   * message string.
   *
   * @param  s  The string to be parsed as a client certificate access log
   *            message.
   *
   * @throws  LogException  If the provided string cannot be parsed as a valid
   *                        log message.
   */
  public ClientCertificateAccessLogMessage(final String s)
         throws LogException
  {
    this(new LogMessage(s));
  }



  /**
   * Creates a new connect access log message from the provided log message.
   *
   * @param  m  The log message to be parsed as a connect access log message.
   */
  public ClientCertificateAccessLogMessage(final LogMessage m)
  {
    super(m);

    peerSubject   = getNamedValue("peerSubject");
    issuerSubject = getNamedValue("issuerSubject");
  }



  /**
   * Retrieves the subject of the peer certificate.
   *
   * @return  The subject of the peer certificate, or {@code null} if it is not
   *          included in the log message.
   */
  public String getPeerSubject()
  {
    return peerSubject;
  }



  /**
   * Retrieves the subject of the issuer certificate.
   *
   * @return  The subject of the issuer certificate, or {@code null} if it is
   *          not included in the log message.
   */
  public String getIssuerSubject()
  {
    return issuerSubject;
  }



  /**
   * {@inheritDoc}
   */
  @Override()
  public AccessLogMessageType getMessageType()
  {
    return AccessLogMessageType.CLIENT_CERTIFICATE;
  }
}
