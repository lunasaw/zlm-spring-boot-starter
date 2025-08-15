package io.github.lunasaw.zlm;

import io.github.lunasaw.zlm.hook.param.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ZLM Hook参数类基础测试
 * 验证Hook事件参数的基本功能
 *
 * @author luna
 * @date 2024/1/5
 */
public class ZlmHookParamTest {

    @Test
    void testOnFlowReportHookParam() {
        OnFlowReportHookParam param = new OnFlowReportHookParam();

        // 测试基本属性设置和获取（这些属性在实际类中存在）
        param.setApp("live");
        param.setStream("test-stream");
        param.setDuration(120);
        param.setPlayer(true);
        param.setSchema("rtsp");
        param.setTotalBytes(1024000);
        param.setVhost("__defaultVhost__");
        param.setIp("192.168.1.100");
        param.setPort(1935);
        param.setId("test-connection-id");
        param.setParams("token=abc123");

        // 验证设置的值
        assertEquals("live", param.getApp());
        assertEquals("test-stream", param.getStream());
        assertEquals(120, param.getDuration());
        assertTrue(param.isPlayer());
        assertEquals("rtsp", param.getSchema());
        assertEquals(1024000, param.getTotalBytes());
        assertEquals("__defaultVhost__", param.getVhost());
        assertEquals("192.168.1.100", param.getIp());
        assertEquals(1935, param.getPort());
        assertEquals("test-connection-id", param.getId());
        assertEquals("token=abc123", param.getParams());
    }

    @Test
    void testOnSendRtpStoppedHookParam() {
        OnSendRtpStoppedHookParam param = new OnSendRtpStoppedHookParam();

        // 测试基本属性（只测试已知存在的属性）
        param.setApp("live");
        param.setStream("rtp-stream");

        assertEquals("live", param.getApp());
        assertEquals("rtp-stream", param.getStream());
        assertNotNull(param);
    }

    @Test
    void testBasicHookParamCreation() {
        // 测试基本Hook参数对象创建
        assertDoesNotThrow(() -> {
            OnFlowReportHookParam flowParam = new OnFlowReportHookParam();
            assertNotNull(flowParam);

            OnSendRtpStoppedHookParam rtpParam = new OnSendRtpStoppedHookParam();
            assertNotNull(rtpParam);

            OnPlayHookParam playParam = new OnPlayHookParam();
            assertNotNull(playParam);

            OnPublishHookParam publishParam = new OnPublishHookParam();
            assertNotNull(publishParam);
        });
    }

    @Test
    void testHookParamInheritance() {
        // 测试参数类继承关系
        OnFlowReportHookParam flowParam = new OnFlowReportHookParam();
        OnSendRtpStoppedHookParam rtpParam = new OnSendRtpStoppedHookParam();

        // 验证都是HookParam的子类
        assertTrue(flowParam instanceof HookParam);
        assertTrue(rtpParam instanceof HookParam);

        // 验证基本的对象创建和非空检查
        assertNotNull(flowParam);
        assertNotNull(rtpParam);
    }

    @Test
    void testHookParamEquality() {
        // 测试参数对象相等性（基于Lombok @Data注解）
        OnSendRtpStoppedHookParam param1 = new OnSendRtpStoppedHookParam();
        OnSendRtpStoppedHookParam param2 = new OnSendRtpStoppedHookParam();

        param1.setApp("test");
        param1.setStream("stream1");

        param2.setApp("test");
        param2.setStream("stream1");

        assertEquals(param1, param2);
        assertEquals(param1.hashCode(), param2.hashCode());

        // 测试不相等情况
        param2.setStream("stream2");
        assertNotEquals(param1, param2);
    }

    @Test
    void testToStringMethod() {
        // 测试toString方法（Lombok @Data生成）
        OnSendRtpStoppedHookParam param = new OnSendRtpStoppedHookParam();
        param.setApp("test-app");
        param.setStream("test-stream");

        String toString = param.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("test-app"));
        assertTrue(toString.contains("test-stream"));
    }
}