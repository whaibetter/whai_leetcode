local userHashKey = KEYS[1] -- 用户领取记录 Hash 表键
local redPacketListKey = KEYS[2] -- 红包列表键
local userRecordListKey = KEYS[3] -- 用户领取记录列表键
local userId = ARGV[1] -- 用户 ID

-- 检查用户是否已经领取过红包
local exists = redis.call('HEXISTS', userHashKey, userId)
if exists == 1 then
    return nil
end

-- 从红包列表中取出一条红包数据
local redPacketData = redis.call('RPOP', redPacketListKey)
if not redPacketData then
    return nil
end

-- 解析红包数据
local redPacket = cjson.decode(redPacketData)

-- 存储用户领取记录
redis.call('HSET', userHashKey, userId, redPacketData)

-- 将红包领取信息存入用户领取记录列表
redis.call('LPUSH', userRecordListKey, redPacketData)

return redPacket
