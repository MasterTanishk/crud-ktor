class ProductsController < ApplicationController
    before_action :set_product, only: [:show, :edit, :update, :destroy]
    protect_from_forgery unless: -> { request.format.json? }
    def index
        @products = Product.all
        respond_to do |format|
#             format.html
            format.json {render json: @products}
        end
    end

    def show
        respond_to do |format|
#             format.html
            format.json {render json: @product}
        end
    end

    def new
        @product = Product.new
    end

    def create
        @product = Product.new(product_params)
        respond_to do |format|
            if @product.save
                format.json { render json: @product, status: :created}
            else
#                 format.html { render :new}
                format.json { render json: @product.errors, status: :unprocessable_entity }
            end
        end
    end

    def edit
    end

    def update
        respond_to do |format|
            if @product.update(product_params)
#                 format.html { redirect to @product, notice: 'Product was successfully updated!' }
                format.json { render json: @product, status: :ok }
            else
#                 format.html { render :edit }
                format.json { render json: @product.errors , status: :unprocessable_entity }
            end
        end
    end

    def destroy
        @product.destroy
        respond_to do |format|
            format.json { head :no_content }
        end
    end

    private

    def set_product
        @product = Product.find(params[:id])
    end

    def product_params
        params.require(:product).permit(:name, :price, :stock_quantity)
    end

end
