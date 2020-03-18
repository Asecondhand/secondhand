package com.secondhand.module.product.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.secondhand.module.product.entity.ProductPic;
import com.secondhand.module.product.mapper.ProductPicMapper;
import com.secondhand.module.sys.service.ProductPicService;
@Service
public class ProductPicServiceImpl extends ServiceImpl<ProductPicMapper, ProductPic> implements ProductPicService{

}
