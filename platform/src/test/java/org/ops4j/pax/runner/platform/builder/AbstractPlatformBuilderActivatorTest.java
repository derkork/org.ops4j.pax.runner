/*
 * Copyright 2007 Alin Dreghiciu.
 *
 * Licensed  under the  Apache License,  Version 2.0  (the "License");
 * you may not use  this file  except in  compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed  under the  License is distributed on an "AS IS" BASIS,
 * WITHOUT  WARRANTIES OR CONDITIONS  OF ANY KIND, either  express  or
 * implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ops4j.pax.runner.platform.builder;

import java.util.Dictionary;
import static org.easymock.EasyMock.*;
import org.junit.Test;
import org.ops4j.pax.runner.platform.PlatformBuilder;
import org.osgi.framework.BundleContext;

public class AbstractPlatformBuilderActivatorTest
{

    @Test( expected = IllegalArgumentException.class )
    public void startWithNullBundleContext()
        throws Exception
    {
        new TestAPBActivator().start( null );
    }

    @Test
    public void start()
        throws Exception
    {
        BundleContext context = createMock( BundleContext.class );
        expect( context.registerService(
            eq( PlatformBuilder.class.getName() ),
            isA( PlatformBuilder.class ),
            (Dictionary) notNull()
        )
        ).andReturn( null );
        replay( context );
        new TestAPBActivator().start( context );
        verify( context );
    }

    @Test( expected = IllegalArgumentException.class )
    public void stopWithNullBundleContext()
        throws Exception
    {
        new TestAPBActivator().stop( null );
    }

    @Test
    public void stop()
        throws Exception
    {
        BundleContext context = createMock( BundleContext.class );
        replay( context );
        new TestAPBActivator().stop( context );
        verify( context );
    }

    private class TestAPBActivator extends AbstractPlatformBuilderActivator
    {

        @Override
        protected String getProviderVersion()
        {
            return "version";
        }

        @Override
        protected String getProviderName()
        {
            return "provider";
        }

        @Override
        protected PlatformBuilder createPlatformBuilder( final BundleContext bundleContext )
        {
            return createMock( PlatformBuilder.class );
        }

    }

}